/* eslint-disable */

import { HttpRestClient } from "@/api/http-rest-client";
import { CompaniesState, CompanyState, MatchesState, PriceState } from "@/model";
import { ActionContext } from "vuex";
import { EntityFactory } from "../entity-factory";

const state: CompaniesState = {
  items: [],
  prices: [],
  dailyPrice: [],
  company: null
}

// getters

const getters = {
  getItems(state: CompaniesState) {
    return state.items;
  },

  getPrices(state: CompaniesState): PriceState[] {
    return state.prices;
  },

  getDailyPrice(state: CompaniesState): string[] {
    return state.dailyPrice;
  },

  getCompany(state: CompaniesState) {
    return state.company;
  },
}

// actions
const client = new HttpRestClient();

const actions = {

  async fetch({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, keywords: string) {
    await client.fetch(`search?keywords=${keywords.toUpperCase()}`).then(response => {
      if (response) {
        const items = response.data.data.bestMatches.map((i: any) => EntityFactory.getEntity('matches', i));
        commit('setItems', items)
      }
    })
  },

  async fetchPrices({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, company: string) {
    await client.fetch(`prices?company=${company}`).then(response => {
      if (response) {
        const items = response.data.data.prices.map((i: any) => EntityFactory.getEntity('price', i));
        commit('setPrices', items)
      }
    })
  },

  async fetchCompany({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, symbol: string) {
    await client.fetch(`company?symbol=${symbol}`).then(response => {
      if (response) {
        const item = EntityFactory.getEntity('company', response.data.data);
        commit('setCompany', item)
      }
    })
  },

  async setDailyPrice({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, date: string) {
    const price: PriceState | undefined = await state.prices.find((p: PriceState) => p.date === date);
    commit('setDailyPrice', price ? Object.values(price).slice(1, 5) : []);
  },

  async setCompany({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, item: MatchesState) {
    dispatch('fetchPrices', item.symbol);
    dispatch('fetchCompany', item.symbol);
  },

  reset({ commit }: ActionContext<CompaniesState, {}>) {
    commit('reset');
  }

}

const mutations = {
  setItems(state: CompaniesState, items: MatchesState[]) {
    state.items = items;
  },

  setPrices(state: CompaniesState, prices: PriceState[]) {
    state.prices = prices;
  },

  setDailyPrice(state: CompaniesState, dailyPrice: string[]) {
    state.dailyPrice = dailyPrice;
  },

  setCompany(state: CompaniesState, company: CompanyState) {
    state.company = company;
  },

  reset(state: CompaniesState) {
    state.items.length = 0;
    state.prices.length = 0;
    state.company = null;
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
