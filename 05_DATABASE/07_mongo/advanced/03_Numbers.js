/*
    insertMany
*/
// 약 20초 소요
for (let i=0; i < 20000; i++) {
    db.numbers.insertOne({
        num: i,
    })
}

// 약 2초 소요
const docs = [];

for (let i=0; i < 20000; i++){
    docs.push({
        num: i
    })
}

db.numbers_2.insertMany(docs)