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
            case 'propertytypeedit':
            return html`
            <propertytype-edit-page></propertytype-edit-page>
            `;
            case 'propertytypelist':
            return html`
            <propertytype-search-list-page></propertytype-search-list-page>
            `;
            case 'languagetypeedit':
            return html`
            <languagetype-edit-page></languagetype-edit-page>
            `;
            case 'languagetypelist':
            return html`
            <languagetype-search-list-page></languagetype-search-list-page>
            `;
            case 'templateedit':
            return html`
            <template-edit-page></template-edit-page>
            `;
            case 'templatelist':
            return html`
            <template-search-list-page></template-search-list-page>
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
