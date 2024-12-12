(require '[clojure.string :as s])
(require '[clojure.pprint :refer [pprint]])
(require '[ubergraph.core :as g])
(require '[ubergraph.alg :as a])

(defn ->grid [input]
  (->> input
       (map-indexed (fn [r row]
                      (map-indexed (fn [c v] [[r c] v]) row)))
       (apply concat)
       (into {})))

(defn adj [v]
  (let [move (partial mapv +)
        directions [[0 1] [0 -1] [1 0] [-1 0]]]
    (map move directions (repeat v))))

(defn ->graph [grid]
  (let [points (keys grid)
        edges (for [p points
                    n (adj p)
                    :when (= (grid p) (grid n))]
                [p n])]
    (-> (g/graph)
        (g/add-nodes* points)
        (g/add-edges* edges))))

(def parse (comp ->graph ->grid s/split-lines slurp))

(defn walls [region]
  [region
   (->> region (mapcat adj) (remove region))])

(defn part-1 [file]
  (let [price #(reduce * (map count %))]
    (->> (parse file)
         a/connected-components
         (map set)
         (map walls)
         (map price)
         (reduce +))))

(pprint (time (part-1 "puzzle-input.txt")))
