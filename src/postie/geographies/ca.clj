(ns postie.geographies.ca
  (:require [malli.generator :as g]
            [clojure.string :as string]
            [postie.geographies.common :refer [preprocess postcode-patterns]]))

(def preprocess-ca
  (comp (fn [postcode]
          (let [thing (clojure.string/upper-case postcode)]
            [(subs thing 0 3) (subs thing 3)]))
        preprocess))

(defn valid?
  ([postcode]
   (->> postcode
       preprocess-ca
       (apply valid?)))
  ([fsa* lsa*]
   (let [{:keys [lsa fsa]} (:ca postcode-patterns)]
     (some? (and (re-matches lsa lsa*)
                 (re-matches fsa fsa*))))))

(defn format-postcode [postcode]
  (let [[fsa lsa] (preprocess-ca postcode)]
    (if (valid? fsa lsa)
      (clojure.string/join " "
                           [fsa lsa])
      false)))

(def generator
  (g/generator [:re (get-in postcode-patterns [:ca :complete])]))
