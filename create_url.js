const hook = process.env.DISCORD_WEBHOOK;
const branch = process.env.BRANCH;
const group = process.env.MOD_GROUP.toString();
const artifact = process.env.ARCHIVES_BASE_NAME;
const version = process.env.MOD_VERSION;
const repo = "https://maven.stardustmodding.org/public-snapshots";

const url = `${repo}/${group.replace(/\./g, "/")}/${artifact}/${version}/${artifact}-${version}.jar`;
const src = `${repo}/${group.replace(/\./g, "/")}/${artifact}/${version}/${artifact}-${version}-sources.jar`;

const data = {
    content: null,
    embeds: [
        {
            title: "Build succeeded on branch " + branch,
            color: 5814783,
            fields: [
                {
                    name: "Mod `.jar`",
                    value: url,
                },
                {
                    name: "Sources `.jar`",
                    value: src,
                }
            ]
        }
    ],
    attachments: []
};

const main = async () => {
    await fetch(hook, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    });
};

main();
