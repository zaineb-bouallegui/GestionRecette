import { Injectable } from '@angular/core';
import Keycloak, { KeycloakInstance } from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  public keycloak: KeycloakInstance | undefined;

  constructor() {
    const keycloakConfig = {
      url: 'http://localhost:8080/auth',
      realm: 'JobBoardKeycloack',
      clientId: 'plat-service',
      initOptions: {
        onLoad: 'login-required',
        silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
      },
    };
    this.keycloak = new Keycloak(keycloakConfig);
  }

  async init(): Promise<void> {
    if (this.keycloak) {
      try {
        await this.keycloak.init({ onLoad: 'login-required' });
      } catch (error) {
        console.error('Keycloak initialization error', error);
      }
    }
  }

  getToken(): string | null {
    return this.keycloak && this.keycloak.token ? this.keycloak.token : null;
  }
}
