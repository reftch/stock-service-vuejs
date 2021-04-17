/* eslint-disable */
import { MatchesState, PriceState, CompanyState } from '../model/index';

export class EntityFactory {

  static readonly MATCHES = 'matches';
  static readonly COMPANY = 'company';
  static readonly PRICE = 'price';

  static getEntity(collection: string, i: any) {
    switch (collection) {
      case this.MATCHES: return EntityFactory.getMatches(i);
      case this.COMPANY: return EntityFactory.getCompany(i);
      case this.PRICE: return EntityFactory.getPrice(i);
      default: return null;
    }
  }

  private static getMatches(i: any): MatchesState {
    return {
      symbol: i.symbol,
      name: i.name,
      type: i.type,
      region: i.region,
      marketOpen: i.marketOpen,
      marketClose: i.marketClose,
      timezone: i.timezone,
      currency: i.currency,
      matchScore: i.matchScore
    };
  }

  private static getPrice(i: any): PriceState {
    return {
      date: i.date,
      open: i.open,
      high: i.high,
      low: i.low,
      close: i.close,
      volume: i.volume,
    };
  }

  private static getCompany(i: any): CompanyState {
    return {
      symbol: i.symbol,
      assetType: i.assetType,
      name: i.name,
      description: i.description,
      currency: i.currency,
      country: i.country,
      sector: i.sector,
      industry: i.industry,
      address: i.address,
      employees: i.employees
    };
  }

}