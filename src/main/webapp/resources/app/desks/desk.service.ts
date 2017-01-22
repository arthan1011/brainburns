/**
 * Created by arthan on 18.01.2017. | Project brainburns
*/

import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";
import {Http, Response} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/find';

import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

const URL_DESKS = "/api/desk";

@Injectable()
export class DeskService {

    constructor(private http: Http) {}

    desksSubject: Subject<Desk[]> = new Subject();

    getDesks(): Observable<Desk[]> {
        this.refreshDesks();
        return this.desksSubject;
    }

    getDesk(id: number): Observable<Desk> {
        return this.getDesks()
            .map(desks => desks.find(desk => desk.id === id));
    }

    createDesk(desk: Desk) {
        this.http.post(URL_DESKS, desk)
            .do(res => this.refreshDesks())
            .subscribe(
                (res: Response) => console.log(res.json()),
                (res: Response) => console.error(res.json())
            );
    }

    private refreshDesks() {
        this.http.get(URL_DESKS)
            .map((res: Response) => res.json().data)
            .subscribe(
                (items: Desk[]) => this.desksSubject.next(items),
                error => console.log(error)
            );
    }
}