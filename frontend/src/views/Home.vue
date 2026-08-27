<template>
    <div class="home">
        <section class="hero-image">
            <img src="../assets/images/Hike.jpeg" id="hero-background" alt="hero image" />
        </section>

        <section class="announcements">
            <h2 id="announcement-title">
                Announcements
            </h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </section>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>
    </div>



    <section class="test">
        <h2>This is the most boring thing ever man, fuck !!!</h2>
    </section>
</template>

<style>
.home {
    display: grid;
    color: #ad5252;
}

.test {
    color: black;
}

.hero-image {
    display: flex;
    position: relative;
    max-width: 100dvw;
    height: 40dvh;
    overflow: hidden;
    border-bottom-right-radius: 10px;
    border-bottom-left-radius: 10px;
}


#hero-background {
    display: flex;
    position: absolute;
    width: auto;
    height: auto;
    top: -110px;
    left: -50px;
}
</style>