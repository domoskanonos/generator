import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {ProjectModel} from "../model/project-model";

export class ProjectRemoteRepository extends BasicRemoteRepository<ProjectModel, number> {

    private static uniqueInstance: ProjectRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"PROJECT");
    }

    static getUniqueInstance() {
        if (!ProjectRemoteRepository.uniqueInstance) {
            ProjectRemoteRepository.uniqueInstance = new ProjectRemoteRepository();
        }
        return ProjectRemoteRepository.uniqueInstance;
    }

}