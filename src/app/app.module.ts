import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CompetitionsComponent } from './components/competitions/competitions.component';
import { HomeComponent } from './components/home/home.component';
import { C3StructureComponent } from './components/c3-structure/c3-structure.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { ContactComponent } from './components/contact/contact.component';
import { SponsorshipComponent } from './components/sponsorship/sponsorship.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { C3CupComponent } from './components/c3-cup/c3-cup.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionsComponent,
    HomeComponent,
    C3StructureComponent,
    ResourcesComponent,
    ContactComponent,
    SponsorshipComponent,
    C3CupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
