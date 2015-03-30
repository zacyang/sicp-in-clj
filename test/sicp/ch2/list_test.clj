(ns sicp.ch2.list-test
    (:use [clojure.test :refer :all]
        [sicp.ch2.list :refer :all]))

(deftest basic-list-ref-test
  (testing "should return first element when there's only one element in the col"
    (is (list-ref [1] 0) 1))
  (testing "should return 2nd element when there are two elements in the col"
    (is (list-ref [1 2] 1) 2))
)
