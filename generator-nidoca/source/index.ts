import messageAppDE from './i18n/message-de.json';
import messageAppEN from './i18n/message-en.json';

import {I18nService, LanguageKey} from '@domoskanonos/frontend-basis';

I18nService.getUniqueInstance().addData(messageAppDE);
I18nService.getUniqueInstance().addData(messageAppEN, LanguageKey.EN);

import './components/property-edit';
import './components/property-list';
import './components/property-combobox';
import './components/item-edit';
import './components/item-list';
import './components/item-combobox';
import './components/project-edit';
import './components/project-list';
import './components/project-combobox';
import './components/process-edit';
import './components/process-list';
import './components/process-combobox';
import './components/propertytype-edit';
import './components/propertytype-list';
import './components/propertytype-combobox';
import './components/languagetype-edit';
import './components/languagetype-list';
import './components/languagetype-combobox';
import './components/template-edit';
import './components/template-list';
import './components/template-combobox';

import './pages/page-dashboard';
import './pages/page-settings';
import './pages/page-register';
import './pages/page-register-ok';
import './pages/page-login';
import './pages/page-logout';
import './pages/page-change-password';
import './pages/page-reset-password';
import './pages/page-terms-of-use';
import './pages/page-default';

import './pages/page-property-edit';
import './pages/page-property-list';
import './pages/page-item-edit';
import './pages/page-item-list';
import './pages/page-project-edit';
import './pages/page-project-list';
import './pages/page-process-edit';
import './pages/page-process-list';
import './pages/page-propertytype-edit';
import './pages/page-propertytype-list';
import './pages/page-languagetype-edit';
import './pages/page-languagetype-list';
import './pages/page-template-edit';
import './pages/page-template-list';


import './app';

import './index.css';

export * from '@domoskanonos/nidoca-app';
