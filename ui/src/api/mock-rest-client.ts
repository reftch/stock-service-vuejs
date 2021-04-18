/* eslint-disable */
import axios from 'axios';
import { RestClient } from './rest-client';

const fetch = (mockData: any, time = 0) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(mockData)
    }, time)
  })
}

export class MockRestClient implements RestClient {

  fetch(parameter?: string | undefined): Promise<any> {
    return fetch(this.resolvePath(parameter), 500);
  }

  private async resolvePath(entityPath: string | undefined) {
    let url = `mock/data/${entityPath}`;
    url = MockRestClient.replaceAll(url, '?', '_');
    url = MockRestClient.replaceAll(url, '%5B', '[');
    url = MockRestClient.replaceAll(url, '%5D', ']');
    url += '.json'
    const response = await axios.get(url);
    return response;
  }

  static replaceAll(str: string, strToReplace: string, strToBe: string) {
    return str.split(strToReplace).join(strToBe);
  }

}