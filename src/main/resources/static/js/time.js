const MINUTE = 60
const HOUR = 60 * MINUTE;
const DAY = 24 * HOUR;
const WEEK = 7 * DAY;
const MONTH = 4 * WEEK;
const YEAR = 12 * MONTH;

export const MONTH_NAMES = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
];

export function getTimeElapsed(selectedMillisecond) {

    const timeElapsedInSeconds = (Date.now() - selectedMillisecond) / 1000;

    let result = 0;
    let prefix = "";

    if (timeElapsedInSeconds < MINUTE) {
        result = timeElapsedInSeconds;
        if(result < 1) return " just now";
        prefix = " seconds ago";
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

export function formatDateFromMilliseconds(milliseconds) {
    const date = new Date(milliseconds);
    const day = date.getDate();

    const month = MONTH_NAMES[date.getMonth()];
    const year = date.getFullYear();

    console.log(year)

    return `${day} ${month} ${year}`;
}

export function calculateDuration(startDate, endDate) {
    // Calculate the difference in milliseconds
    const durationInMilliseconds = endDate - startDate;

    // Calculate the number of months and the remainder in days
    const months = Math.floor(durationInMilliseconds / (30.44 * 24 * 60 * 60 * 1000));
    const days = (durationInMilliseconds % (30.44 * 24 * 60 * 60 * 1000)) / (24 * 60 * 60 * 1000);

    if (months < 12) {
        return `${months} months`;
    } else {
        const years = Math.floor(months / 12);
        const remainingMonths = months % 12;
        if (remainingMonths === 0) {
            return `${years} year${years > 1 ? 's' : ''}`;
        } else {
            return `${years} year${years > 1 ? 's' : ''} and ${remainingMonths} month${remainingMonths > 1 ? 's' : ''}`;
        }
    }
}

export function calculateEndDate(startDate, months) {
    const date = new Date(startDate);

    console.log(date)

    // Calculate the year and month to be set
    console.log(parseInt(date.getMonth()) + parseInt(months))
    console.log(date.getMonth() + parseInt(months) / 12)

    let targetYear = date.getFullYear() + Math.floor((date.getMonth() + parseInt(months)) / 12);

    console.log(targetYear)

    let targetMonth = (date.getMonth() + parseInt(months)) % 12 + 1; // Add 1 here

    console.log(targetMonth)

    // Set the date to the desired month and year
    date.setMonth(targetMonth - 1); // Subtract 1 here
    date.setFullYear(targetYear);

    console.log(date);

    return date;
}

export function formatDate(timestamp) {
    // Assuming data.solved_date is a timestamp in milliseconds
    const date = new Date(timestamp);

    // Format the date as a string (adjust the format as needed)
    const formattedDate = `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`;

    return formattedDate;
}