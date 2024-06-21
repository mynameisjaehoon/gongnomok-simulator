import isoDateToFormatString from "../../../global/date";

export default function SingleComment({ 
  info,
  handleReport,
  handleDelete
}) {

  function dateFormatting() {

  }

  return (
    <>
      <div className="comment-item-root">
        <div>
          <div className="comment-item-top-container">
            <article className="comment-item-nickname">{info.name}</article>
            <span className="comment-created-time">{isoDateToFormatString(info.createdDate)}</span>
          </div>
          <article>{info.content}</article>
        </div>
        <div className="comment-item-menu-container">
          <div className="comment-item-siren-container" onClick={handleReport}>
            <img className="comment-item-siren-icon" src="/images/etc/siren.png"></img>
          </div>
          <div className="comment-item-delete-container" onClick={handleDelete}>
            <img className="comment-item-delete-icon" src="/images/etc/trashcan.png"></img>
          </div>
        </div>
      </div>
    </>
  );
}