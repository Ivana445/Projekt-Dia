import {ItemModel} from "./item.module";

export interface ListModel {
    id?: number;
    name: string;
    deadline?: Date;
    items?: ItemModel[];
    share?: string[];
}
