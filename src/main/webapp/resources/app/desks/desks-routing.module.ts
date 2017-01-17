/**
 * Created by arthan on 17.01.2017.
 */

import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {DeskDetailComponent} from "./desk-detail.component";
import {DesksComponent} from "./desks.component";

const appRoutes = [
    {
        path: "desks",
        component: DesksComponent,
        children: [
            {
                path: ":id",
                component: DeskDetailComponent
            }
        ]
    },

];

@NgModule({
    imports: [
        RouterModule.forChild(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class DesksRoutingModule {}