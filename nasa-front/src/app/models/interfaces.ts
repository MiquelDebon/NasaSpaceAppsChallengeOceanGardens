import { action0, action1, action2, action3, action4 } from "./temes";
import { activityType1, activityType2, activityType3 } from "./activity-types";

export interface UserForSignup{
    name: string,
    phytoplanktonName: string,
    email: string,
    password: string
}

export interface UserForLogin{
    email: string,
    password: string
}
export interface User {
    id?: string;
    email: string,
    password?: string; //key?
    name: string,
    phytoplankton: phytoState
}



export interface UserWithId extends User {
    id: string
}

export interface phytoState {
    name: string,
    health: number,
    co2Consumed: number,
    reproductions: number,
    inSymbiosis: boolean
}

export type Action = typeof action0 | typeof action1 | typeof action2 | typeof action3 | typeof action4;
export type ActivityType = typeof activityType1 | typeof activityType2 | typeof activityType3;

export type Answer = string[]; // o string? o object?

export interface Activity {
    id?: number,
    action: Action,
    type: ActivityType,
    statement: string,
    options?: Answer,
    solution: Answer,
    extraInfo: string,
    assets?: string[]
}

export interface SolvedActivity extends Activity{
    userAnswer: Answer;
    points: boolean;
}

export interface SolvedActivityResult {
    id: string,
    action: Action,
    points: boolean
}