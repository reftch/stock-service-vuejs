/* eslint-disable */

import { HttpRestClient } from "@/api/http-rest-client";
import { CompaniesState, CompanyState } from "@/model";
import { ActionContext } from "vuex";
import { EntityFactory } from "../entity-factory";

const state: CompaniesState = {
  items: [],
  item: null
}

// getters

const getters = {
  getItems(state: CompaniesState) {
    return state.items;
  },

  getItem(state: CompaniesState) {
    return state.item;
  },
}

// actions
const client = new HttpRestClient();

const actions = {

  async fetch({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, keywords: string) {
    await client.fetch(`companies?keywords=${keywords}`).then(response => {
      if (response) {
        const items = response.data.data.bestMatches.map((i: any) => EntityFactory.getEntity('company', i));
        // console.log(items)
        commit('setItems', items)
      }
    })
  },

  setItem({ dispatch, commit, state, rootGetters }: ActionContext<CompaniesState, {}>, item: CompanyState) {
    commit('setItem', item);
  },

  reset({ commit }: ActionContext<CompaniesState, {}>) {
    commit('reset');
  }

}

const mutations = {
  setItems(state: CompaniesState, items: CompanyState[]) {
    state.items = items;
  },

  setItem(state: CompaniesState, item: CompanyState) {
    state.item = item;
  },

  reset(state: CompaniesState) {
    state.items.length = 0;
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
