import { useEffect, useState, useRef } from "react";

import ItemCondition from "./condition/ItemCondition";
import ItemList from "./ItemList";
import axios from "axios";

import { DEFAULT_FETCH_SIZE } from "../../global/item";
import { BASE_URI } from "../../global/uri";
import FeedbackBanner from "../banner/FeedbackBanner";
import InformBanner from "../banner/InformBanner";
import { INITIAL_SEARCH_CONDITION } from "./condition/search";
import ItemRanking from "./ItemRanking";

export default function ItemMain() {

  const [itemList, setItemList] = useState([]);  
  const [isItemLoaded, setIsItemLoaded] = useState(false);
  const nextPage = useRef(0);
  const [hasNextPage, setHasNextPage] = useState(true);
  
  useEffect(() => {
    searchItems();
  }, []);

  function searchItems() {
    setIsItemLoaded(false);
    axios
      .get(`${BASE_URI}/api/items?page=0&size=${DEFAULT_FETCH_SIZE}`)
      .then((res) => {
        const items = res?.data?.items;
        if (items === undefined || items === null) {
          setItemList([]);
        } else {
          setItemList(items);
        }
        setIsItemLoaded(true);

        if (res.data.items?.length < DEFAULT_FETCH_SIZE) setHasNextPage(false);
        else setHasNextPage(true);

        nextPage.current += 1
      })
      .catch((err) => {
        console.log(err)
        setItemList([]); // 에러 발생시 아무런 아이템도 나타내지 않는다.
      });
  }

  function searchItemsWithCondition(searchCondition) {
    axios
      .post(`${BASE_URI}/api/items?page=${nextPage.current}&size=${DEFAULT_FETCH_SIZE}`, searchCondition, { withCredentials: true })
      .then((res) => {
        if (nextPage.current == 0) {
          setItemList([...res.data.items])
        } else {
          setItemList([...itemList, ...res.data.items])
        }
        setIsItemLoaded(true)

        if (res.data.items?.length < DEFAULT_FETCH_SIZE) setHasNextPage(false);
        else setHasNextPage(true);  
        nextPage.current += 1
      })
      .catch((err) => {
        console.log(err)
        setItemList([]);
      })
  }

  function doSearch(e, searchCondition) {
    e.preventDefault();
    searchItemsWithCondition(searchCondition);
  }

  function handleMoreItemButton(e, searchCondition) {
    e.preventDefault()
    searchItemsWithCondition(searchCondition);
  }

  const [searchCondition, setSearchCondition] = useState(INITIAL_SEARCH_CONDITION);

  function handleItemNameChange(e) {
    e.preventDefault();
    nextPage.current = 0
    let copy = {...searchCondition};
    copy.name = e.target.value;
    setSearchCondition(copy);
  }

  function handleJobsChange(e, jobName) {
    e.preventDefault();
    nextPage.current = 0;
    // jobName에 따라서 올바른 속성을 true로 변경한 후 condition 값을 세팅한다.
    let copy = {...searchCondition}
    switch (jobName) {
      case 'warrior':
        copy.jobs.warrior = !copy.jobs.warrior; break;
      case 'bowman':
        copy.jobs.bowman = !copy.jobs.bowman; break;
      case 'magician':
        copy.jobs.magician = !copy.jobs.magician; break;
      case 'thief':
        copy.jobs.thief = !copy.jobs.thief; break;
    }

    setSearchCondition(copy)
    searchItemsWithCondition(copy);
  }

  function handleMinLevelChange(e) {
    nextPage.current = 0;
    let copy = {...searchCondition};
    copy.minLevel = e.target.value;
    setSearchCondition(copy);
  }

  function handleCategoryChange(e, category) {
    e.preventDefault();
    nextPage.current = 0;
    let copy = {...searchCondition};
    if (copy.category === category) {
      copy.category = "ALL";
    } else {
      copy.category = category;
    }
    setSearchCondition(copy);
    searchItemsWithCondition(copy);
  }

  return (
    <>
      <section className="mt-3">
        <div className="row mb-3">
          <section>
            <FeedbackBanner/>
            <InformBanner/>
          </section>
        </div>
        <div className="row">
          <div className="col-lg-12 col-xl-4">
            <section className="col-md-12">
              <ItemCondition
                searchCondition={searchCondition}
                handleItemNameChange={handleItemNameChange}
                handleJobsChange={handleJobsChange}
                handleCategoryChange={handleCategoryChange}
                handleMinLevelChange={handleMinLevelChange}
                doSearch={doSearch}
              />
            </section>
            <ItemRanking/>
          </div>  
          <div className="col-lg-12 col-xl-8">
            <section>
              <ItemList 
                searchCondition={searchCondition}
                itemList={itemList} 
                isItemLoaded={isItemLoaded}
                hasNextPage={hasNextPage}
                handleMoreItemButton={handleMoreItemButton}
              />
            </section>
          </div>
        </div>
      </section>

    </>
  ) 
}