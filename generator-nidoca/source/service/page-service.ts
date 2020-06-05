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
            case 'propertyedit':
            return html`
            <${model.editPageTagName}></${model.editPageTagName}>
            `;
            case 'propertylist':
            return html`
            <property-search-list-page></property-search-list-page>
            `;
            case 'itemedit':
            return html`
            <${model.editPageTagName}></${model.editPageTagName}>
            `;
            case 'itemlist':
            return html`
            <item-search-list-page></item-search-list-page>
            `;
            case 'projectedit':
            return html`
            <${model.editPageTagName}></${model.editPageTagName}>
            `;
            case 'projectlist':
            return html`
            <project-search-list-page></project-search-list-page>
            `;
            case 'processedit':
            return html`
            <${model.editPageTagName}></${model.editPageTagName}>
            `;
            case 'processlist':
            return html`
            <process-search-list-page></process-search-list-page>
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
