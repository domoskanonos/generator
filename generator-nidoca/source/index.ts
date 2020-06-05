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


import './app';

import './index.css';

export * from '@domoskanonos/nidoca-app';
