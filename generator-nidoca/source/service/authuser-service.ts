export class AuthUserRemoteService {

    private static uniqueInstance: AuthUserRemoteService;

    constructor() {
    }

    static getUniqueInstance() {
        if (!AuthUserRemoteService.uniqueInstance) {
            AuthUserRemoteService.uniqueInstance = new AuthUserRemoteService();
        }
        return AuthUserRemoteService.uniqueInstance;
    }

}