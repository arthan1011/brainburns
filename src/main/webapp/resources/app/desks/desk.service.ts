/**
 * Created by arthan on 18.01.2017.
 */

import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";

const DESKS = [
    new Desk(1, "English"),
    new Desk(2, "Kanji"),
    new Desk(3, "Word")
];
let desksPromise = Promise.resolve(DESKS);

@Injectable()
export class DeskService {
    getDesks(): Desk[] {
        return DESKS;
    }

    getDesk(id: number) {
        return desksPromise
            .then(desks => desks.find(desk => desk.id === id));
    }
}