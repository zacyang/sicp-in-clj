(ns sicp.ch3.bankacount_refactored
  (:use [midje.sweet]
        [sicp.ch3.bankacount_refactored])
  )

(fact "3.3 account mgr with pwd"
      (let [fn-under-test (-> (make-account 100) (with-security "secret-password"))]
        (((fn-under-test "secret-password") :withdraw) 40)  => 60
        (((fn-under-test "invalid-password") :withdraw) 60)  => "Incorrect password"
        
))

(fact "3.4 account access try limit"
      (let [fn-under-test (-> (make-account 100) (with-try-limit 3))]
        (((fn-under-test)  :withdraw) 10) =>  90
        (((fn-under-test)  :withdraw) 10) =>  80
        (((fn-under-test)  :withdraw) 10) =>  70
        (((fn-under-test)  :withdraw) 10) => "Limit exceeded, policy is informed!"
))

(fact "3.4 account access try limit"
      (let [fn-under-test (make-account-with-try-limit 3 "pwd" 100)]
        ((fn-under-test "invalid-pwd" :withdraw) 10) =>  "Incorrect password"
        ((fn-under-test "invalid-pwd" :withdraw) 10) =>  "Incorrect password"
        ((fn-under-test "invalid-pwd" :withdraw) 10) =>  "Incorrect password"
        ((fn-under-test "invalid-pwd" :withdraw) 10) => "Limit exceeded, policy is informed!"
))
