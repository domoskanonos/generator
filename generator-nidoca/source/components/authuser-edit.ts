import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {AuthUser} from '../model/authuser-model';
import {AuthUserRemoteRepository} from "../repo/authuser-repository";

@customElement('authuser-edit-component')
export class AuthUserEditComponent extends NidocaAbstractComponentEdit<AuthUser> {

    async getItemById(identifier: any): Promise<AuthUser> {
        return AuthUserRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: AuthUser): Promise<AuthUser> {
        return AuthUserRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: AuthUser): Promise<AuthUser> {
        return AuthUserRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return AuthUserRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    email : string = '';
    @property()
    password : string = '';
    @property()
    firstName : string = '';
    @property()
    lastName : string = '';
    @property()
    birthday : Date = new Date();
    @property()
    active : boolean = false;
    @property()
    acceptTermsOfUse : boolean = false;
    @property()
    city : string = '';
    @property()
    roles : any[] = [];

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.email}"
                    name="email"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_email')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.password}"
                    name="password"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_password')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.firstName}"
                    name="firstName"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_firstName')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.lastName}"
                    name="lastName"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_lastName')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.birthday}"
                    name="birthday"
                    inputfieldType="${InputfieldType.DATE}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_birthday')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .checked="${this.active}"
                    name="active"
                    inputfieldType="${InputfieldType.SWITCH}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_active')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .checked="${this.acceptTermsOfUse}"
                    name="acceptTermsOfUse"
                    inputfieldType="${InputfieldType.SWITCH}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_acceptTermsOfUse')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.city}"
                    name="city"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_city')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.roles}"
                    name="roles"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('authuser_property_roles')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(authuser: AuthUser): void {
        this.email = authuser.email;
        this.password = authuser.password;
        this.firstName = authuser.firstName;
        this.lastName = authuser.lastName;
        this.birthday = authuser.birthday;
        this.active = authuser.active;
        this.acceptTermsOfUse = authuser.acceptTermsOfUse;
        this.city = authuser.city;
        this.roles = authuser.roles;
    }

    getIdentifier(authuser: AuthUser): any {
        return authuser.id;
    }


}
