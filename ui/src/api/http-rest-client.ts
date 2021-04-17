import Axios, { AxiosError } from 'axios';

export class HttpRestClient {

  protected backendUrl = "http://192.168.178.43:3000/api/v1";

  private axios = Axios.create();

  constructor() {
    // Request interceptor
    this.axios.interceptors.request.use((config) => {
      return config;
    }, function (error) {
      return Promise.reject(error);
    });

    // Response interceptor
    this.axios.interceptors.response.use(undefined, (error: AxiosError) => {
      this.errorsValidation(error);
    });
  }
  
  // eslint-disable-next-line
  async fetch(parameter?: string | undefined): Promise<any> {
    const url = parameter ? `${this.backendUrl}/${parameter}` : this.backendUrl;
    return await this.axios.get(`${url}`, this.getOptions());
  }

  private getOptions() {
    return {
      headers: {
        'Accept': 'application/vnd.api+json',
        'Content-Type': 'application/vnd.api+json;',
      }
    }
  }

  private errorsValidation(err: AxiosError) {
    if (err.response && err.response.status) {
      // some error handler
    }
  }

}