(ns postie.geographies.au
  (:require [malli.generator :as g]
            [postie.geographies.common :refer [preprocess]]))

(def pattern #"^[0-9]{4}$")

(defn valid?
  [postcode]
  (->> postcode
       preprocess
       (re-matches pattern)
       some?))

(defn format-postcode [postcode]
  (let [sanitised-postcode (preprocess postcode)]
    (if (valid? sanitised-postcode)
      sanitised-postcode
      false)))

(def generator
  (g/generator [:re pattern]))

(def strategy
  {:generator generator
   :formatter format-postcode
   :valid? valid?})
