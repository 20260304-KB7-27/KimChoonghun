/*
    insertOne()
    - 하나의 문서를 컬렉션에 추가
    db.컬렉션명.insertOne({ 키 : 밸류, ... } )

    insertMany()
    - 여러개의 문서
    - db.컬렉션명.insertMany([{키 : 밸류, ... }, {키 : 밸류, ...} ... ])
*/

db.users.insertOne({ username : "smith"})
db.users.insertOne({ username : "jones"})
db.users.insertOne({ food: "cake" })

/*
    updateOne() // updateMany()
    - 조건에 맞는 첫번째 문서를 수정
    - db.컬렉션명.updateOne({조건}, {$set: {수정할 필드}})
*/
db.users.updateOne(
    {username : "smith"},
    {
        $set : {
            favorites: {
                cities: ['Chicago', 'Seoul'],
                movies: ['Casablanca', 'For a few Dollars More', 'The Sting']
            }
        }
    }
)

db.users.updateOne(
    {username : "jones"},
    {
        $set : {
            favorites: {
                movies: ['Casablanca', 'Rocky']
            }
        }
    }
)

/*
    find() / findOne()
    - 컬렉션에서 문서 조회
    - db.컬렉션명.find({조건}, {프로젝션})
*/

// 카사블랑카 영화를 좋아하는 사람들
db.users.find({"favorites.movies": "Casablanca"})

// 맨 처음 조건에 맞게 찾은 1개의 문서만 반환
db.users.findOne({"favorites.movies": "Casablanca"})

// 원하는 필드만 조회
db.users.findOne({"favorites.movies": "Casablanca"}, {username : 1})

db.users.updateMany({"favorites.movies": "Casablanca"},
    {
        // addToSet : 중복 방지용 문법, 이미 있으면 무시, 없으면 업데이트
        $addToSet : {"favorites.movies": "Rocky"}
    }
)

/*
    replaceOne()
    - 조건에 맞는 문서를 새문서로 교체
    - 기존 문서의 필드가 모두 삭제되고, 새 필드로 대체됨
 */
db.users.replaceOne({username: "smith"},
    {
        country: "Canada"
    }
)

db.users.updateOne({country: "Canada"}, {
    $set : {username : "smith"}
})

db.users.updateOne({username: "smith"}, {
    // unset : 해당 필드를 제거
    $unset : {country : ""}
})

/*
    deleteOne() / deleteMany()
    - 조건에 맞는 문서 삭제
 */
db.users.deleteOne({username : 'smith'}) // smith라는 username을 가진 데이터만 삭제
db.users.deleteMany({}) // 모든 문서 삭제 (조건 X)

db.users.find()

/*
    drop()
    - 컬렉션을 삭제
*/
db.users.drop()