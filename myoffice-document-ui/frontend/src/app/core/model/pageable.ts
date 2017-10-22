import {Page} from "./page";

export interface Pageable<T> extends Page {
    content: Array<T>;
    sort: string;
    first: boolean;
}
