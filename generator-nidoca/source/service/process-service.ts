export class ProcessRemoteService {

    private static uniqueInstance: ProcessRemoteService;

    constructor() {
    }

    static getUniqueInstance() {
        if (!ProcessRemoteService.uniqueInstance) {
            ProcessRemoteService.uniqueInstance = new ProcessRemoteService();
        }
        return ProcessRemoteService.uniqueInstance;
    }

}