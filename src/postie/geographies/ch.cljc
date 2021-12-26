(ns postie.geographies.ch
  (:require [malli.generator :as g]
            [postie.geographies.common :refer [preprocess default-formatter]]))

(def pattern #"^[1-9][0-9]{3}$")

(defn valid? [postcode]
  (->> postcode
      preprocess
      (re-matches pattern)
      some?))

(def format-postcode (partial default-formatter valid?))

(def generator
  (g/generator [:re pattern]))

(def strategy
  {:generator generator
   :formatter format-postcode
   :valid? valid?})

