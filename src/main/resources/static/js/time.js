const MINUTE = 60
const HOUR = 60 * MINUTE;
const DAY = 24 * HOUR;
const WEEK = 7 * DAY;
const MONTH = 4 * WEEK;
const YEAR = 12 * MONTH;

export function getTimeElapsed(selectedMillisecond) {

    const timeElapsedInSeconds = (Date.now() - selectedMillisecond) / 1000;

    let result = 0;
    let prefix = "";

    if (timeElapsedInSeconds < MINUTE) {
        result = timeElapsedInSeconds;
        prefix = result > 1 ? " seconds ago" : " second ago";
    } else if (timeElapsedInSeconds < HOUR) {
        result = Math.floor(timeElapsedInSeconds / MINUTE);
        prefix = result > 1 ? " minutes ago" : " minute ago";
    } else if (timeElapsedInSeconds < DAY) {
        result = Math.floor(timeElapsedInSeconds / HOUR);
        prefix = result > 1 ? " hours ago" : " hour ago";
    } else if (timeElapsedInSeconds < WEEK) {
        result = Math.floor(timeElapsedInSeconds / DAY);
        prefix = result > 1 ? " days ago" : " day ago";
    } else if (timeElapsedInSeconds < MONTH) {
        result = Math.floor(timeElapsedInSeconds / WEEK);
        prefix = result > 1 ? " weeks ago" : " week ago";
    } else if (timeElapsedInSeconds < YEAR) {
        result = Math.floor(timeElapsedInSeconds / MONTH);
        prefix = result > 1 ? " months ago" : " month ago";
    } else {
        result = Math.floor(timeElapsedInSeconds / YEAR);
        prefix = result > 1 ? " years ago" : " year ago";
    }

    return result + prefix;
}