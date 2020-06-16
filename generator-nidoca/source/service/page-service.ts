import {html, TemplateResult} from 'lit-element';
import {RouterService, I18nService} from '@domoskanonos/frontend-basis';

export class PageService {
    private static instance: PageService;

    private constructor() {
    }

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
            case 'authuseredit':
            return html`
            <authuser-edit-page></authuser-edit-page>
            `;
            case 'authuserlist':
            return html`
            <authuser-search-list-page></authuser-search-list-page>
            `;
            case 'settings':
            return html`
               <page-settings
                  navigationTitle="${I18nService.getUniqueInstance().getValue(
                     'settings'
                  )}"
               ></page-settings>
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
