import { Activity } from "src/app/models/interfaces"
import { action1, action2, action3, action4 } from "src/app/models/temes"
import { activityType1, activityType2, activityType3 } from "src/app/models/activity-types"

interface ActivitiesList {
    [action1]: Activity[],
    [action2]: Activity[],
    [action3]: Activity[],
    [action4]: Activity[]

}

export const activitiesList: ActivitiesList = {
    [action1]: [
    {
    action: action1,
    type: activityType1, 
    statement: "Plankton is often called invisible superheroes because they perform ________?",
    options: ['Acrobatics', 'Photosynthesis', 'Magic tricks'],
    solution: [
            "Photosynthesis"
        ],
    extraInfo: "They're invisible superheroes! Plankton does photosynthesis, like plants, and takes carbon dioxide from the air and turns it into fresh oxygen that we all need to breathe..."
    },
{
	action: action1,
        type: activityType2,
        statement: "Order this sentence correctly",
        options: ['of the fish', 'in the sea', 'we are the food'],
        solution: ['we are the food', 'of the fish', 'in the sea'],
        extraInfo: "They're like a vacuum cleaner. They absorb carbon dioxide, which is the gas that heats our planet, and store it at the bottom of the ocean."
    },
    {
    action: action1,
    type: activityType1,
    statement: "Plankton helps cool the Earth by ________ sunlight.?",
    options: ['Reflecting', 'Evaporating', 'Absorbing'
        ],
    solution: [
            "Reflecting"
        ],
    extraInfo: 'They help cool the Earth. When plankton grows, it reflects the sunlight back into space, keeping the planet cool...'
    }
],
    [action2]: [
        {
        action: action2,
        type: activityType1, 
        statement: "Plankton plays a crucial role in the ocean's food chain, and if it disappears, ------.?",
        options: ['They become invisible', 'The whole ecosystem could be affected', 'The ocean gets colder'
            ],
        solution: [
                "The whole ecosystem could be affected"
            ],
        extraInfo: "They're an important part of the food chain. If plankton disappears, all the animals that feed on it would too..."
        },
        {
	action: action2,
        type: activityType2,
        statement: "Order this sentence correctly",
        options: ['millions of years', 'we have been', 'on earth for'],
        solution: ['we have been', 'on earth for', 'millions of years'],
        extraInfo: "Changes in ocean temperature and acidity can affect plankton, which in turn affects the entire marine ecosystem."
}

        // {
        //     action: action2,
        //     type: activityType2,
        //     statement: "Ordena de posterior a anterior",
        //     options: ['b', 'c', 'a'],
        //     solution: ["c", "b", "a"],
        //     extraInfo: 'Sabies que fdsaf'
        // },  
    ],
    [action3]: [
        {
        action: action3,
        type: activityType1, 
        statement: "Plankton, like tiny astronauts in the -------.?",
        options: ['Space', 'Forest', 'Ocean'
            ],
        solution: [
                "Ocean"
            ],
        extraInfo: 'Plankton is tiny! They are tiny creatures that float in the water, like astronauts in aquatic space...'
        },
        {
	action: action3,
        type: activityType2,
        statement: "Order this sentence correctly",
        options: ['satellites', 'scientists study', 'us with space'],
        solution: ['scientists study', 'us with space', 'satellites'],
        extraInfo: "Plankton is truly amazing and powerful! Despite being tiny, its role in climate balance is gigantic."
}
        // {
        //     action: action3,
        //     type: activityType2,
        //     statement: "Ordena de posterior a anterior",
        //     options: ['b', 'c', 'a'],
        //     solution: ["c", "b", "a"],
        //     extraInfo: 'Sabies que fdsaf'
        // },    
    ],
    [action4]: [
{
    action: action4,
    type: activityType1, 
    statement: "Phytoplankton in the farms feed on ______, just like they're sunbathing all day!",
    options: ['Moonlight', 'Starlight', 'Sunlight '
        ],
    solution: [
            "Sunlight"
        ],
    extraInfo: "Phytoplankton in the farms feed on sunlight, just like they're sunbathing all day!, Phytoplankton in the farms can also help fight climate change by absorbing carbon dioxide from the air, like an ocean superhero..."
    },
    {
    action: action4,
    type: activityType1,
    statement: "When phytoplankton grows in farms, it helps keep the oceans clean and provides food for many ------.",
    options: ['Land animals', 'Marine critters', 'Flying birds'
        ],
    solution: [
            "Marine critters"
        ],
    extraInfo: 'When phytoplankton grows in farms, it helps keep the oceans clean and provides food for many marine critters, Imagine phytoplankton farms as supermarkets for fish, where they find delicious and fresh food....'
    },
{
	action: action4,
        type: activityType2,
        statement: "Order this sentence correctly",
        options: ['the ocean for us all', 'to live on together', 'scientists create farms in'],
        solution: ['scientists create farms in', 'he ocean for us all', 'to live on together'],
        extraInfo: "Phytoplankton in the farms feed on sunlight, just like they're sunbathing all day!"
}
    ]
}