import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { appConfig } from './app.config';
import { ROUTES } from '@angular/router';
import { AppShellComponent } from './app-shell/app-shell.component';
import {provideServerRendering} from "@angular/platform-server";

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(),
    {
        provide: ROUTES,
        multi: true,
        useValue: [
            {
                path: 'shell',
                component: AppShellComponent
            }
        ]
    }
]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
