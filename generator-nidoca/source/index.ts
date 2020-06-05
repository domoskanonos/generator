import messageAppDE from './i18n/message-de.json';
import messageAppEN from './i18n/message-en.json';

import {I18nService, LanguageKey} from '@domoskanonos/frontend-basis';

I18nService.getUniqueInstance().addData(messageAppDE);
I18nService.getUniqueInstance().addData(messageAppEN, LanguageKey.EN);

import './components/property-edit';
import './components/property-list';
import './components/item-edit';
import './components/item-list';
import './components/project-edit';
import './components/project-list';
import './components/process-edit';
import './components/process-list';

import './pages/page-dashboard';
import './page-settings';
import './page-register';
import './page-register-ok';
import './page-login';
import './page-logout';
import './page-change-password';
import './page-reset-password';
import './page-terms-of-use';
import './page-default';

import './pages/property-edit';
import './pages/property-list';
import './pages/item-edit';
import './pages/item-list';
import './pages/project-edit';
import './pages/project-list';
import './pages/process-edit';
import './pages/process-list';


import './app';

import './index.css';

export * from '@domoskanonos/nidoca-app';
