import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./components/home/home.component";
import { CompetitionsComponent } from "./components/competitions/competitions.component";
import { C3StructureComponent } from "./components/c3-structure/c3-structure.component";
import { ResourcesComponent } from "./components/resources/resources.component";
import { ContactComponent } from "./components/contact/contact.component";
import { SponsorshipComponent } from "./components/sponsorship/sponsorship.component";
import { C3CupComponent } from "./components/c3-cup/c3-cup.component";
import { AdminComponent } from "./components/admin/admin.component";
import { InformationComponent } from "./components/information/information.component";
const routes: Routes = [
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full",
  },
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "c3-cup",
    component: C3CupComponent,
  },
  {
    path: "competitions",
    component: CompetitionsComponent,
  },
  {
    path: "team-C3",
    component: C3StructureComponent,
  },
  {
    path: "resources",
    component: ResourcesComponent,
  },
  {
    path: "contact",
    component: ContactComponent,
  },
  {
    path: "sponsorship",
    component: SponsorshipComponent,
  },
  {
    path: "admin",
    component: AdminComponent,
  },
  {
    path: "Nats20",
    component: InformationComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
