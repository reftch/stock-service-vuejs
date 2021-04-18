import { RestClient } from './rest-client';
import { HttpRestClient } from './http-rest-client';
import { MockRestClient } from './mock-rest-client';

export class RestClientRegistry {

  private static clients = new Map<string, RestClient>();

  /**
   * Sets the REST client to use.
   *
   * @param name the REST client's name
   * @param client the REST client to use
   */
  private static setClient(name: string, client: RestClient) {
    RestClientRegistry.clients.set(name, client);
  }

  /**
   * Returns the REST client. If it has not been set yet, it throws an error instead.
   */
  public static getClient(): RestClient {
    const mode = process.env.NODE_ENV;
    let client = this.clients.get(mode);
    if (!client) {
      client = mode === 'test' ? new MockRestClient() : new HttpRestClient();
      RestClientRegistry.setClient(mode, client);
    }
    return client;
  }

}