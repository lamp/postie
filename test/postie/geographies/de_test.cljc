(ns postie.geographies.de-test
  (:require [clojure.test :refer [deftest testing is]]
            [postie.geographies.de :as sut]
            [malli.generator :as g]))

(deftest valid?-test
  (testing "with valid postcode"
    (is (true? (sut/valid? "88131"))))
  (testing "with valid but badly formatted postcode"
    (is (true? (sut/valid? "     88131  "))))
  (testing "invalid postcodes"
    (is (false? (sut/valid? "ABCDE")))
    (is (false? (sut/valid? "W8813")))))

(deftest format-postcode-test
  (testing "With a valid postcode"
    (is (= "88131" (sut/format-postcode "88131"))))

  (testing "with badly formatted postcode"
    (is (= "88131" (sut/format-postcode "     88131    "))))

  (testing "with invalid postcode"
    (is (false? (sut/format-postcode "ABCDE")))))

(deftest generator-test
  (testing "generates a valid postcode"
    (is (sut/valid? (g/generate sut/generator)))))
