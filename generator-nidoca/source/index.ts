import messageAppDE from './i18n/message-de.json';
import messageAppEN from './i18n/message-en.json';

import {I18nService, LanguageKey} from '@domoskanonos/frontend-basis';

I18nService.getUniqueInstance().addData(messageAppDE);
I18nService.getUniqueInstance().addData(messageAppEN, LanguageKey.EN);

import './components/authuser-edit';
import './components/authuser-list';
import './components/authuser-combobox';

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

import './pages/page-authuser-edit';
import './pages/page-authuser-list';


import './app';

import './index.css';

export * from '@domoskanonos/nidoca-app';
