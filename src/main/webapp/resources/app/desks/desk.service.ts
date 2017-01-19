/**
 * Created by arthan on 18.01.2017.
 */

import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";
import {Http, Response} from "@angular/http";

import 'rxjs/add/operator/toPromise';

const URL_DESKS = "/api/desk";

@Injectable()
export class DeskService {

    constructor(private http: Http) {}

    getDesks(): Promise<Desk[]> {
        return this.http.get(URL_DESKS)
            .toPromise()
            .then((res:Response) => res.json().data)
            .catch(error => console.log(error));
    }

    getDesk(id: number) {
        return this.getDesks()
            .then(desks => desks.find(desk => desk.id === id));
    }
}