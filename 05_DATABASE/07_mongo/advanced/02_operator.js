db.members.find()

// .sort() : 정렬
// 객체안에 정렬을 원하는 필드 작성 ( 1: 오름차순 / -1 : 내림차순 )
db.members.find().sort({age : -1})

// office 기준으로 오름차순 정렬 후
// 동일 값에 대해서는 나이 내림차순
db.members.find().sort({office : 1, age : -1})

// limit(n) : n개만큼 도큐먼트 출력
db.members.find().sort({office : 1, age : -1}).limit(5)

/*
    조건연산자
    $or : 여러 조건 중 하나라도 만족하는 문서를 찾기
    $and : 모든 조건을 만족하는 문서를 찾기
    $in : 지정한 값중 하나라도 일치하는 문서 조회
*/
db.members.find({
    $or : [
        { office : '서울' },
        { age : 1 }
    ]
})

db.members.find({
    $and : [
        { office : 'busan' },
        { age : 1 }
    ]
})

db.members.find({
    office : { $in: ['busan', 'seoul'] }
})

/*
   비교 연산
   $gt (greater than)
   $lt (less than)
   $gte (greater than or equal)
   $lte (less than or equal)
   $ne : (not equal)

*/

// 나이가 50보다 많은 사람들
db.members.find({
    age : { $gt : 50 }
})

// 나이가 50보다 적은 사람들
db.members.find({
    age : { $lt : 50 }
})

// 나이가 20 이상 30 이하인 사람들
db.members.find({
    age : {
        $gte : 20, // 20 이상
        $lte : 30 // 30 이하
    }
})

db.members.find({
    office: { $ne : "busan" }
})

/*
    $exists
    - 해당 필드가 존재에 따라 찾기
    true : 필드가 있으면 조회
    false : 필드가 없으면 조회
*/
db.members.updateMany(
    {
        age : {
            $gte : 1,
            $lte : 10
        }
    },
    {
        $unset: {phone: ''}
    }
)

db.members.find()

db.members.find({
        // $not : 부정연산자
        phone: {
            $not:
                {
                    $exists: true
                }
        }
    }
)

/*
    $regex
    - 정규표현식을 이용한 검색
*/
db.members.find({
    // 이름이 G로 시작하는
    name : {$regex : /^G/}
})