/**
 * Created by Arthan on 06.01.2017. Project: brainburns
 */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CardFormComponent} from "./card-form.component";
import {DashboardComponent} from "./dashboard.component";

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'card-new',
        component: CardFormComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {}
