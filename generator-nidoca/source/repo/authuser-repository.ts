import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {AuthUser} from "../model/authuser-model";

export class AuthUserRemoteRepository extends BasicRemoteRepository<AuthUser, number> {

    private static uniqueInstance: AuthUserRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"/AUTHUSER");
    }

    static getUniqueInstance() {
        if (!AuthUserRemoteRepository.uniqueInstance) {
            AuthUserRemoteRepository.uniqueInstance = new AuthUserRemoteRepository();
        }
        return AuthUserRemoteRepository.uniqueInstance;
    }

}