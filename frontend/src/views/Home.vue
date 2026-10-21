<template>
    <div class="home">
        <section class="hero-section">

            <section class="hero-overlay">
                <h2 id="hero-title">Welcome Home</h2>
                <p id="des-hero">Meet the holy and the warm hands of the Praise community at Holy Trinity</p>
                <article class="overlay-bts">
                    <button id="bt-plan-visit">Plan Your Visit</button>
                    <button id="bt-sermons">Watch Sermons</button>
                </article>

            </section>

        </section>


        <section class="announcements">
            <article class="act-panel">
                <img src="../assets/images/Marathon.jpeg" id="event-panel-bg" alt="event panel background" />
                <details id="act-info">
                    <p></p>
                </details>
            </article>

            <article class="upd-announcements">

                <nav class="announcements-nav">
                    <button><img src="../assets/images/notifications-icon.png" id="notifications-main-icon"
                            alt="announcement icon" />
                        <p>All</p>
                    </button>
                    <article class="updates-panel">
                        <button id="btn-announcements"></button>
                        <button id="btn-announcements"></button>
                        <button id="btn-announcements"></button>
                    </article>
                </nav>
                <article class="rc-announcements">

                    <article class="update-info">
                        <img src="../assets/images/announcements.png" id="notification-icon" alt="announcement icon" />
                        <article id="msg-text">
                            <h3>Announcement</h3>
                            <p></p>
                            <h4>thur, 18:45pm</h4>
                        </article>

                    </article>
                    <article class="update-info">

                        <img src="../assets/images/sermons-icon.png" id="notification-icon"
                            alt="sermons notification icon" />
                        <article id="msg-text">
                            <h3>Sermons</h3>
                            <p></p>
                            <h4>thur, 14:20pm</h4>
                        </article>

                    </article>
                    <article class="update-info">

                        <img src="../assets/images/events-icon.png" id="notification-icon"
                            alt="events notification icon" />
                        <article id="msg-text">
                            <h3>Events</h3>
                            <p></p>
                            <h4>tue, 10:15am</h4>
                        </article>
                    </article>
                </article>
            </article>
        </section>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>
        <article class="layout">
            <aside class="events-layout1">
                <h2 id="events-layout1-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
            <aside class="events-layout2">
                <h2 id="events-layout2-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
        </article>
    </div>

</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const images = {
    img1: new URL("../assets/images/marathon.jpeg", import.meta.url).href,
    img2: new URL("../assets/images/welcome.jpeg", import.meta.url).href,
    img3: new URL("../assets/images/Hike.jpeg", import.meta.url).href,
    img4: new URL("../assets/images/Campus-meeting.jpeg", import.meta.url).href,
    img5: new URL("../assets/images/Gathering.jpeg", import.meta.url).href,
}

const eventType = {
    marathon: document.querySelector("marathon-tag"),
    gathering: document.querySelector("gathering-tag"),
    yFriday: document.querySelector("yFriday-tag")
}


</script>

<style src="../styles/home.css">
</style>