(ns sicp.extra.route-test
  (:use [sicp.extra.route :refer :all]
        [clojure.test :refer :all]))

(deftest basic-routing-test
  (testing "should return best route"
    (is (astar [0,0] 
               900
               world)
        [])))
