
export interface CompaniesState {
  items: MatchesState[];
  company: CompanyState | null;
  prices: PriceState[];
  dailyPrice: string[]
}

export interface MatchesState {
  symbol: string;
  name?: string;
  type?: string;
  region?: string;
  marketOpen?: string;
  marketClose?: string;
  timezone?: string;
  currency?: string;
  matchScore?: string;
}

export interface PriceState {
  date: string;
  open: string;
  high: string;
  low: string;
  close: string;
  volume: string;
}

export interface CompanyState {
  symbol: string;
  assetType: string;
  name: string;
  description: string;
  currency: string;
  country: string;
  sector: string;
  industry: string;
  address: string;
  employees: string;
}