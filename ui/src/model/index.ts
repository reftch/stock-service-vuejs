export interface Entity {

}

export interface CompaniesState extends Entity {
  items: CompanyState[];
  item: CompanyState | null;
}

export interface CompanyState extends Entity {
  symbol: string;
  name: string;
  type: string;
  region: string;
  marketOpen: string;
  marketClose: string;
  timezone: string;
  currency: string;
  matchScore: string;
}
