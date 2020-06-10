import {html, TemplateResult} from 'lit-element';
import {RouterService, I18nService} from '@domoskanonos/frontend-basis';

export class PageService {
  private static instance: PageService;

  private constructor() {}

  static getUniqueInstance() {
    if (!PageService.instance) {
      PageService.instance = new PageService();
    }
    return PageService.instance;
  }

  renderPage(): TemplateResult {
    let currentPage = RouterService.getUniqueInstance().getCurrentPage();
    console.log('render page: '.concat(currentPage));
    switch (currentPage) {
      case 'propertyedit':
        return html`
          <property-edit-page></property-edit-page>
        `;
      case 'propertylist':
        return html`
          <property-search-list-page></property-search-list-page>
        `;
      case 'itemedit':
        return html`
          <item-edit-page></item-edit-page>
        `;
      case 'itemlist':
        return html`
          <item-search-list-page></item-search-list-page>
        `;
      case 'projectedit':
        return html`
          <project-edit-page></project-edit-page>
        `;
      case 'projectlist':
        return html`
          <project-search-list-page></project-search-list-page>
        `;
      case 'processedit':
        return html`
          <process-edit-page></process-edit-page>
        `;
      case 'processlist':
        return html`
          <process-search-list-page></process-search-list-page>
        `;
      case 'register':
        return html`
          <page-register navigationTitle="${I18nService.getUniqueInstance().getValue('register')}"></page-register>
        `;
      case 'register_ok':
        return html`
          <page-register-ok
            navigationTitle="${I18nService.getUniqueInstance().getValue('register_ok')}"
          ></page-register-ok>
        `;
      case 'login':
        return html`
          <page-login navigationTitle="${I18nService.getUniqueInstance().getValue('login')}"></page-login>
        `;
      case 'logout':
        return html`
          <page-logout navigationTitle="${I18nService.getUniqueInstance().getValue('logout')}"></page-logout>
        `;
      case 'change_password':
        return html`
          <page-change-password
            navigationTitle="${I18nService.getUniqueInstance().getValue('change_password')}"
          ></page-change-password>
        `;
      case 'reset_password':
        return html`
          <page-reset-password
            navigationTitle="${I18nService.getUniqueInstance().getValue('reset_password')}"
          ></page-reset-password>
        `;

      case 'terms_of_use':
        return html`
          <page-terms-of-use
            navigationTitle="${I18nService.getUniqueInstance().getValue('terms_of_use')}"
          ></page-terms-of-use>
        `;
      case 'settings':
        return html`
          <page-settings navigationTitle="${I18nService.getUniqueInstance().getValue('settings')}"></page-settings>
        `;

      case '':
      case 'dashboard':
      default:
        return html`
          <page-dashboard></page-dashboard>
        `;
    }
  }
}
