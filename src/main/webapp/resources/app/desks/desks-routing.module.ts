/**
 * Created by arthan on 17.01.2017. | Project brainburns
*/

import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {DeskDetailComponent} from "./desk-detail.component";
import {DesksComponent} from "./desks.component";
import {DeskNewFormComponent} from "./desk-new-form.component";
import {CardFormComponent} from "./card-form.component";

const appRoutes = [
    {
        path: "desks",
        component: DesksComponent,
        children: [
            {
                path: "new",
                component: DeskNewFormComponent
            },
            {
                path: "card/new",
                component: CardFormComponent
            },
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