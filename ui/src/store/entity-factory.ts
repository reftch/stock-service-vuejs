/* eslint-disable */
import { CompanyState } from '../model/index';

export class EntityFactory {

  static readonly COMPANY = 'company';

  static getEntity(collection: string, i: any) {
    switch (collection) {
      case this.COMPANY: return EntityFactory.getCompany(i);
      default: return null;
    }
  }

  private static getCompany(i: any): CompanyState {
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


}