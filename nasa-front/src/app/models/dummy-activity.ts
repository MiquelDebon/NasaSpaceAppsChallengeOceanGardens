import { Activity } from "./interfaces"

export const dummyActivity: Activity = {
    action: 1,
    type: 'select',
    statement: '2+2?',
    options: ['1', '2', '4', '6'],
    solution: ['4'],
    extraInfo: 'Sabies que blabla?'
}