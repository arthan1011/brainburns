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

    allDesks: Desk[];

    getDesks(): Promise<Desk[]> {
        if (!this.allDesks) {
            return this.http.get(URL_DESKS)
                .toPromise()
                .then((res: Response) => this.allDesks = res.json().data)
                .catch(error => console.log(error));
        }
        return Promise.resolve(this.allDesks);
    }

    getDesk(id: number) {
        return this.getDesks()
            .then(desks => desks.find(desk => desk.id === id));
    }

    createDesk(desk: Desk) {
        this.http.post(URL_DESKS, desk)
            .subscribe(
                (res: Response) => console.log(res.json()),
                (res: Response) => console.error(res.json())
            );
    }
}