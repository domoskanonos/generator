export class ProjectRemoteService {

    private static uniqueInstance: ProjectRemoteService;

    constructor() {
    }

    static getUniqueInstance() {
        if (!ProjectRemoteService.uniqueInstance) {
            ProjectRemoteService.uniqueInstance = new ProjectRemoteService();
        }
        return ProjectRemoteService.uniqueInstance;
    }

}